package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    private Ticket first = new Ticket(1, 3100, "VKO", "LED", 90);
    private Ticket second = new Ticket(2, 6000, "DME", "KZN", 140);
    private Ticket third = new Ticket(3, 9100, "SVO", "OSL", 160);
    private Ticket fourth = new Ticket(4, 3500, "DME", "LED", 90);
    private Ticket fifth = new Ticket(5, 6500, "VKO", "KZN", 140);
    private Ticket sixth = new Ticket(6, 12000, "SVO", "ROM", 230);
    private Ticket seventh = new Ticket(7, 3000, "VKO", "LED", 95);

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
    }

    @Test
    public void shouldFindTicket() {

        Ticket[] expected = new Ticket[]{second};
        Ticket[] actual = manager.searchBy("DME", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMoreOneTicket() {

        Ticket[] expected = new Ticket[]{first, seventh};
        Ticket[] actual = manager.searchBy("VKO", "LED");

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldNotFindTicket() {

        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.searchBy("ROM", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortByPrice() {

        Ticket[] expected = new Ticket[]{seventh, first, fourth, second, fifth, third, sixth};
        Ticket[] actual = new Ticket[]{first, fourth, seventh, second, sixth, fifth, third};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }
}