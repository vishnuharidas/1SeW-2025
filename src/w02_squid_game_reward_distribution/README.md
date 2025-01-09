# Simulating Squid Game S2 Prize Money Distribution Rules for 456 participants

The code simulates how much money each participant will get if they decide to quit the game by voting at a given point,
and N number of participants are eliminated at that point.

#### In Netflix's "Squid Game", the prize distribution rule is as follows:

- The total prize money is 45.6 billion won.
- For each eliminated player, their individual "value" of 100 million won is added to the prize pool.
- With 456 players at the start, the prize pool grows as players are eliminated. For example:
    - If one player is eliminated, 100 million won is added.
    - If all 455 players (except one winner) are eliminated, the prize pool reaches the full 45.6 billion won.
- **Season 2** allows players to vote after each game to decide whether to continue or stop. If the majority votes to
  stop the games, the accumulated prize money up to that point is divided among the remaining players, and they are
  allowed to leave.

In summary:

1. The prize money starts accumulating with every elimination.
2. If the majority votes to stop the games, the accumulated prize money up to that point is divided among the remaining
   players.
3. The last standing player receives the entire accumulated prize pool of 45.6 billion won.


