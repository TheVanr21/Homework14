package ru.netology;

import ru.netology.domain.Player;
import ru.netology.exception.AlreadyRegisteredException;
import ru.netology.exception.NotRegisteredException;

import java.util.HashMap;

public class Game {

    private HashMap<String, Integer> players = new HashMap<>();

    public HashMap<String, Integer> getPlayers() {
        return players;
    }

    public void register(Player player) {
        if (findByName(player.getName()) == null) {
            players.put(player.getName(), player.getStrength());
        } else {
            throw new AlreadyRegisteredException(player.getName());
        }
    }

    public int round(String playerName1, String playerName2) throws NotRegisteredException {
        HashMap<String, Integer> player1 = findByName(playerName1);
        if (player1 == null) {
            throw new NotRegisteredException(playerName1);
        }
        int player1Strength = player1.get(playerName1);

        HashMap<String, Integer> player2 = findByName(playerName2);
        if (player2 == null) {
            throw new NotRegisteredException(playerName2);
        }
        int player2Strength = player2.get(playerName2);

        if (player1Strength > player2Strength) {
            return 1;
        } else if (player1Strength < player2Strength) {
            return 2;
        } else {
            return 0;
        }
    }

    public HashMap<String, Integer> findByName(String playerName) {
        HashMap<String, Integer> result = new HashMap<>();
        if (players.containsKey(playerName)) {
            result.put(playerName, players.get(playerName));
            return result;
        } else {
            return null;
        }
    }
}
