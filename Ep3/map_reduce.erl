-module(map_reduce).
-export([map_reduce/4]).

-import(lists, [foreach/2, foldl/3]).

map_reduce(FunMap, FunReduce, Acc0, L) ->
	S = self(),
	N = length(L),
	foreach(fun (X) -> spawn(fun () -> S ! FunMap(X) end) end, L),
	reduce_responses(FunReduce, Acc0, N).


reduce_responses(_FunReduce, Acc, 0) -> Acc;
reduce_responses(FunReduce, Acc, N) ->
	receive
		Val ->
			NewAcc = FunReduce(Acc, Val),
			reduce_responses(FunReduce, NewAcc, N - 1)
	end.