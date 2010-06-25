-module(dictionary_server).
-export([start/0,  stop/0,
	 insert/2, remove/1, lookup/1,
	 clear/0,  size/0]).
						


%% Se o servidor já estiver de pé, deve levantar um erro, seja este qual for.
start() ->
    register(dictionary, spawn(fun () -> loop([], 0) end)),
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
    dictionary ! clear,
    cleared.

size() ->
    MyPID = self(),
    dictionary ! {MyPID, size},
    receive
	{MyPID, Size} ->
	    Size
    end.


loop(Dictionary, Size) ->
    receive
	stop ->
	    done;
	clear -> 
	    loop([], 0);
	{From, size} -> 
	    From ! {From, Size},
	    loop(Dictionary, Size);
	{From, insert, Tuple} -> 
	    {NewDictionary, NewSize} = insertInDictionary(Tuple, Dictionary, Size),
	    From ! {From, NewDictionary},
	    loop(NewDictionary, NewSize);
	{From, remove, Key} -> 
	    {NewDictionary, Status, NewSize} = removeFromDictionary(Key, Dictionary, Size),
            From ! {From, Status},
	    loop(NewDictionary, NewSize);
	{From, lookup, Key} -> 
	    Found = lookupInDictionary(Key, Dictionary),
	    From ! {From, Found},
	    loop(Dictionary, Size)
    end.





insertInDictionary({Key, Value}, Dictionary, Size) ->
    case lists:keymember(Key, 1, Dictionary) of
        false ->
            {[{Key, Value} | Dictionary], Size+1};
        true ->
            {lists:keyreplace(Key, 1, Dictionary, {Key, Value}) , Size}
    end.
    



lookupInDictionary(Key, Dictionary) ->
    case lists:keyfind(Key, 1, Dictionary) of
        false ->
	    notfound;
	{Key, Value} ->
	    Value
    end.



removeFromDictionary(Key, Dictionary, Size) ->
    case lists:keymember(Key, 1, Dictionary) of
	false ->
	    {Dictionary, notfound, Size};
	true ->
	    {lists:keydelete(Key, 1, Dictionary), ok, Size-1}
    end.
