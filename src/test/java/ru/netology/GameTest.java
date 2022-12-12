package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Player;
import ru.netology.exception.AlreadyRegisteredException;
import ru.netology.exception.NotRegisteredException;

import java.util.HashMap;

public class GameTest {

    Player player1 = new Player(1, "BACR", 2567);
    Player player2 = new Player(2, "RS", 2153);
    Player player3 = new Player(3, "woogie", 2153);
    Player player4 = new Player(4, "3am6uh", 5210);
    Player player5 = new Player(5, "Zeal", 7230);
    Player player6 = new Player(6, "Daytalt", 5764);

    Game game = new Game();


    @Test
    public void shouldRegister() {
        try {
            game.register(player1);
        } catch (AlreadyRegisteredException ex) {
            //ex.printStackTrace();
        }

        HashMap<String, Player> expected = new HashMap<>();
        expected.put(player1.getName(), player1);
        HashMap<String, Player> actual = game.getPlayers();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotRegisterTwice() {
        try {
            game.register(player1);
            game.register(player1);
        } catch (AlreadyRegisteredException ex) {
            //ex.printStackTrace();
        }

        HashMap<String, Player> expected = new HashMap<>();
        expected.put(player1.getName(), player1);
        HashMap<String, Player> actual = game.getPlayers();

        Assertions.assertEquals(expected, actual);
        Assertions.assertThrows(AlreadyRegisteredException.class, () -> {
            game.register(player1);
            game.register(player1);
        });
    }

    @Test
    public void shouldFindByName() {
        try {
            game.register(player1);
            game.register(player2);
            game.register(player3);
        } catch (AlreadyRegisteredException ex) {
            //ex.printStackTrace();
        }

        Player expected = player3;
        Player actual = game.findByName("woogie");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByWrongName() {
        try {
            game.register(player1);
            game.register(player2);
            game.register(player3);
        } catch (AlreadyRegisteredException ex) {
            //ex.printStackTrace();
        }

        Player expected = null;
        Player actual = game.findByName(player6.getName());

        Assertions.assertEquals(expected, actual);
    }

    @Nested
    public class withRegisteredPlayers {

        @BeforeEach
        public void registerFivePlayers() {
            try {
                game.register(player1);
                game.register(player2);
                game.register(player3);
                game.register(player4);
                game.register(player5);
            } catch (AlreadyRegisteredException ex) {
                //ex.printStackTrace();
            }
        }

        @Test
        public void gameTestDraw() {
            int expected = 0;
            int actual = -1;
            try {
                actual = game.round(player2.getName(), player3.getName());
            } catch (NotRegisteredException ex) {
                //ex.printStackTrace();
            }

            Assertions.assertEquals(expected, actual);
        }

        @Test
        public void gameTestWin1() {
            int expected = 1;
            int actual = -1;
            try {
                actual = game.round(player1.getName(), player3.getName());
            } catch (NotRegisteredException ex) {
                //ex.printStackTrace();
            }

            Assertions.assertEquals(expected, actual);
        }

        @Test
        public void gameTestWin2() {
            int expected = 2;
            int actual = -1;
            try {
                actual = game.round(player4.getName(), player5.getName());
            } catch (NotRegisteredException ex) {
                //ex.printStackTrace();
            }

            Assertions.assertEquals(expected, actual);
        }

        @Test
        public void gameTestNotRegistered1() {
            Assertions.assertThrows(NotRegisteredException.class, () -> {
                int result = game.round(player4.getName(), player6.getName());
            });
        }

        @Test
        public void gameTestNotRegistered2() {
            Assertions.assertThrows(NotRegisteredException.class, () -> {
                int result = game.round(player6.getName(), player1.getName());
            });
        }
    }
}
