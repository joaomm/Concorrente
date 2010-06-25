-module(parte1).
-export([fib/1,
	 adjacent_duplicates/1,
	 deep_sum/1,
	 concatenate_all/1,
	 perimeter/1,
	 permutations/1]).

fib(N) ->
    fib(N, 0, 1).
fib(0, Result, _Next) ->
    Result;
fib(N, Result, Next) ->
    fib(N-1, Result + Next, Result).



adjacent_duplicates(L) ->
    adjacent_duplicates(L, []).
adjacent_duplicates([], Acc) ->
    reverse(Acc);
adjacent_duplicates([_], Acc) ->
    reverse(Acc);
adjacent_duplicates([H1, H2 | T], Acc) ->
    adjacent_duplicates([H2 | T], add_to_list(H1 == H2, H1, Acc)).

add_to_list(true, H, Acc) ->
    [H | Acc];
add_to_list(false, _H, Acc) ->
    Acc.

reverse(L) ->
    reverse(L, []).
reverse([], Acc) ->
    Acc;
reverse([H | T], Acc) ->
    reverse(T, [H | Acc]).



deep_sum(Tuple = {_, _}) ->
    tuple_sum(Tuple);
deep_sum(L) ->
    deep_sum(L, 0).

deep_sum([], Acc) ->
    Acc;
deep_sum(L, Acc) ->
    lists:foldl(fun (Thing, Sum) -> deep_sum(Thing) + Sum end, Acc, L).

tuple_sum({A,B}) ->
    A + B.



concatenate_all([]) ->
    "";
concatenate_all(L) ->
    lists:concat(L).



perimeter({circle, Radius})->
    2 * math:pi() * Radius;
perimeter({rectangle, Width, Height}) ->
    2*Width + 2*Height;
perimeter({right_triangle, Width, Height, Hypot}) ->
    Width + Height + Hypot.


permutations([]) ->
    [];
permutations(L= [_]) ->
    [L];
permutations(L) ->
    [[H|T] || H <- L, T <- permutations(L -- [H])].
