-module(integer_finder).
-export([find_integers/2]).
-import(map_reduce, [map_reduce/4]).

find_integers(L, Func) ->
    SortedList = lists:sort(lists:flatten(L)),
    ListWithoutDuplicates = removeDuplicates(SortedList),
    CreateTuple = fun (X) -> {Func(X), X} end,
    ReversedList = map_reduce(CreateTuple, fun filterBooleans/2,
			      [], ListWithoutDuplicates),
    lists:reverse(ReversedList).



filterBooleans(Acc, {Bool, X}) -> 
    case Bool of
	true ->
	    [X | Acc];
	false ->
	    Acc
    end.


removeDuplicates(L) ->
    removeDuplicates(L, []).
removeDuplicates([], Acc) ->
    lists:reverse(Acc);
removeDuplicates([H|T], Acc) ->
    case lists:member(H, Acc) of
	false ->
	    removeDuplicates(T, [H | Acc]);
	true ->
	    removeDuplicates(T, Acc)
    end.
