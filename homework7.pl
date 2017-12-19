% Question 1

reverse([A|B], L) :- reverse(B, C), append(C, [A], L).

% Question 2

take(_, 0, []) :- !.
take([], _, []).
take([A|B], N, [A|L1]) :- N > 0, X is N - 1, take(B, X, L1).

% Question 3

% A

nleaves(nil, 0).
nleaves(_, Left, Right) :- nleaves(Left, LN), nleaves(Right, RN), N is LN + RN.

% B

treeMember(A, node(A, _, _)).
treeMember(A, node(_, Left, _)) :- treeMember(A, Left).
treeMember(A, node(_, _, Right)) :- treeMember(A, Right).

% C

preOrder(nil, []).
preOrder(node(N, T1, T2), L) :- preOrder(T1, L2), preOrder(T2, L2), append([N|L1], L2, L).

% D

height(nil, 0).
height(node(_, Left, Right), Height) :- height(Left, LHeight), height(Right, RHeight), H is max(LHeight, RHeight) + 1.

% Question 4

insert(A, [], [A]).
insert(A, [B|List], [A, B|List]) :- A < B, !.
insert(A, [B|List2], [B|List]) :- insert(A, List2, List).