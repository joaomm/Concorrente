-module(dictionary_server).
-export([start/0,  stop/0,
	 insert/2, remove/1, lookup/1,
	 clear/0,  size/0]).
						


%% Se o servidor já estiver de pé, deve levantar um erro, seja este qual for.
start() ->
    register(dictionary, spawn(fun () -> loop([]) end)),
    server_started.

%% Se o servidor não estiver de pé, nao faz nada.
stop() -> 
    case whereis(dictionary) of
	undefined ->
	    server_already_stopped;
	_ ->
	    dictionary ! stop
    end.

%% Se Key já existir o valor deverá ser trocado. 
%% A função devolve o atom _ok_ após o trabalho ter sido feito.
insert(Key, Value) -> 
    MyPID = self(),
    dictionary ! {MyPID, insert, {Key, Value}},
    receive
	{MyPID, Dictionary} ->
	    Dictionary
    end.


%% Se a chave for encontrada e removida, devolve o atom _ok_.
%% Caso a chave não seja encontrada devolve o atom _notfound_.     
remove(Key) -> 
    MyPID = self(),
    dictionary ! {MyPID, remove, Key},
    receive
	{MyPID, Status} ->
	    Status
    end.

%% Se a chave for encontrada devolve a tupla {ok, Valor}.
%% Caso a chave não seja encontrada devolve o atom _notfound_. 
lookup(Key) -> 
    MyPID = self(),
    dictionary ! {MyPID, lookup, Key},
    receive
	{MyPID, Found} ->
	    Found
    end.


clear() ->
    cleared.

size() ->
    0.


loop(Dictionary) ->
    receive
	stop ->
	    done;
	{From, insert, Tuple} -> 
	    NewDictionary = insertInDictionary(Tuple, Dictionary),
	    From ! {From, NewDictionary},
	    loop(NewDictionary);
	{From, remove, Key} -> 
	    NewDictionary = removeFromDictionary(Key, Dictionary, []),
	    case NewDictionary == Dictionary of
		true ->
		    From ! {From, notfound};
		false ->
		    From ! {From, ok}
	    end,
	    loop(NewDictionary);
	{From, lookup, Key} -> 
	    Found = lookupInDictionary(Key, Dictionary),
	    From ! {From, Found},
	    loop(Dictionary);
	{From, Msg} -> 
	    From ! Msg,
	    loop(Dictionary)
    end.





insertInDictionary(Tuple, Dictionary) ->
    [Tuple | Dictionary].



lookupInDictionary(_Key, []) ->
    notfound;
lookupInDictionary(Key, [{KeyT, Value} |T]) ->
    case Key == KeyT of
	true ->
	    Value;
	false ->
	    lookupInDictionary(Key, T)
    end.



removeFromDictionary(_Key, [], RemovedDictionary) ->
    RemovedDictionary;
removeFromDictionary(Key, [{KeyT, Value} |T], RemovedDictionary) ->
    case Key == KeyT of
	true ->
	    removeFromDictionary(Key, T, RemovedDictionary);
	false ->
	    removeFromDictionary(Key, T, [{KeyT, Value} | RemovedDictionary])
    end.
