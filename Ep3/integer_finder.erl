-module(integer_finder).
-export([find_integers/2, paralel_sort/1]).
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



paralel_sort(L) ->
    mergesort(L).


split(L) -> 
	split(L, L, []).
split(L1, [], Acc) ->
	{lists:reverse(Acc), L1};
split(L1, [_|[]], Acc) ->
	split(L1, [], Acc);
split([H1 | T1], [_, _ | T2], Acc) ->
	split (T1, T2, [H1 | Acc]).

mergesort ([]) -> 
    [];
mergesort (L = [A]) ->
    L; 
mergesort (L = [A, B]) ->
    sort_list_of_2(L);
mergesort (L = [A, B, C]) -> 
    sort_list_of_3(L);
mergesort (L) ->
	{A, B} = split(L),
	SortedA = mergesortSpawn(A),
	SortedB = mergesortSpawn(B),
	merge(SortedA, SortedB).

mergesortSpawn(L) ->
    MyPid = self(),
    spawn(fun() -> MyPid ! {MyPid, mergesort(L)} end),
    receive
	{MyPid, Sorted} ->
	    Sorted
    end.
	
		   

merge (L, R) -> merge (L, R, []).
merge ([], R, Acc) -> lists:reverse(Acc) ++ R;
merge (L, [], Acc) -> lists:reverse(Acc) ++ L;
merge ([HL|TL], [HR|TR], Acc) ->
	{NewAcc, L, R} = merge_compare (HL, TL, HR, TR, Acc, (HL =< HR)),
	merge(L, R, NewAcc).
	
merge_compare (HL, TL, HR, TR, Acc, true) ->
	{[HL|Acc], TL, [HR|TR]};
merge_compare (HL, TL, HR, TR, Acc, false) ->
	{[HR|Acc], [HL|TL], TR}.

sort_list_of_2([A, B]) ->
    case A =< B of
	true ->
	    [A, B];
	false ->
	    [B, A]
    end.

sort_list_of_3([A, B, C]) ->
    case A =< B of
	true ->
	    case B =< C of
		true ->           [A, B, C];
		false ->
		    case A =< C of 
			true ->   [A, C, B];
			false ->  [C, A, B]
		    end
	    end;
	false -> %  B < A
	    case A =< C of
		true ->           [B, A, C];
		false -> % C < A
		    case B =< C of 
			true ->   [B, C, A];
			false ->  [C, B, A]
		    end
	    end
    end.
