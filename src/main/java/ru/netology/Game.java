package ru.netology;

import ru.netology.domain.Player;
import ru.netology.exception.AlreadyRegisteredException;
import ru.netology.exception.NotRegisteredException;

import java.util.HashMap;

public class Game {

    private HashMap<String, Player> players = new HashMap<>();

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public void register(Player player) {
        if (findByName(player.getName()) == null) {
            players.put(player.getName(), player);
        } else {
            throw new AlreadyRegisteredException(player.getName());
        }
    }

    public int round(String playerName1, String playerName2) throws NotRegisteredException {
        Player player1 = findByName(playerName1);
        if (player1 == null) {
            throw new NotRegisteredException(playerName1);
        }

        Player player2 = findByName(playerName2);
        if (player2 == null) {
            throw new NotRegisteredException(playerName2);
        }

        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }

    public Player findByName(String playerName) {
        return players.getOrDefault(playerName, null);
    }
}
