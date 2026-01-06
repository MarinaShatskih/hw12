import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    public void testSimpleTaskMatches() {
        SimpleTask task = new SimpleTask(123, "Позвонить домой");
        Assertions.assertTrue(task.matches("Позвонить"));

    }

    @Test
    public void testSimpleTaskNotMatches() {
        SimpleTask task = new SimpleTask(123, "Позвонить домой");
        Assertions.assertFalse(task.matches("Набрать"));

    }

    @Test
    public void testEpicMatches() {
        Epic task = new Epic(123, new String[]{"хлеб", "чай", "сыр"});
        Assertions.assertTrue(task.matches("сыр"));

    }

    @Test
    public void testEpicNotMatches() {
        Epic task = new Epic(123, new String[]{"хлеб", "чай", "сыр"});
        Assertions.assertFalse(task.matches("молоко"));

    }

    @Test
    public void testMeetingTopicMatches() {
        Meeting task = new Meeting(123, "Утренний созвон", "Новый проект", "утро");
        Assertions.assertTrue(task.matches("созвон"));

    }

    @Test
    public void testMeetingProlectMatches() {
        Meeting task = new Meeting(123, "Утренний созвон", "Новый проект", "утро");
        Assertions.assertTrue(task.matches("проект"));

    }

    @Test
    public void testMeetingNotMatches() {
        Meeting task = new Meeting(123, "Утренний созвон", "Новый проект", "утро");
        Assertions.assertFalse(task.matches("утро"));

    }

    @Test
    public void testOneTaskSearched() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить хлеб");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Приложение");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testZeroTasksSearched() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить хлеб");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("обед");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testFewTaskSearched() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Хлеб");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("Хлеб");
        Assertions.assertArrayEquals(expected, actual);
    }


}