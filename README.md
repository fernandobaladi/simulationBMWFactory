This is a simulator of a BMW factory where I solved a producer-consumer problem. Here I used threads to simulate many producers as Llantas, Motores and Parabrisas, where all of them has the same producer structure, and consumers that also are threads of Ensamblador.

To solve it I use semaphores, 6 in total, where 3 were of mutual exclution and 3 to synchronize the production between the multiple producers and consumers.
