colorString(X, "blue", Saida) :-
    concatenate(
        ["\u001b[34m",
        X,
        "\u001b[0m"],
        Saida).

colorString(X, "red", Saida) :-
    concatenate(
        ["\u001b[31m",
        X,
        "\u001b[0m"],
        Saida).

colorString(X, "black", Saida) :-
    concatenate(
        ["\u001b[30m",
        X,
        "\u001b[0m"],
        Saida).

colorString(X, "green", Saida) :-
concatenate(
    ["\u001b[32m",
    X,
    "\u001b[0m"],
    Saida).

colorString(X, "yellow", Saida) :-
    concatenate(
        ["\u001b[33m",
        X,
        "\u001b[0m"],
        Saida).

colorString(X, "magenta", Saida) :-
    concatenate(
        ["\u001b[35m",
        X,
        "\u001b[0m"],
        Saida).

colorString(X, "cyan", Saida) :-
    concatenate(
        ["\u001b[36m",
        X,
        "\u001b[0m"],
        Saida).

colorString(X, "white", Saida) :-
    concatenate(
        ["\u001b[3m",
        X,
        "\u001b[0m"],
        Saida).