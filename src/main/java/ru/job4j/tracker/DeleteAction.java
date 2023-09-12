package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "Delete item by id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Delete item ===");
        int id = input.askInt("Enter id: ");
        tracker.delete(id);
        Item item = tracker.findById(id);
        System.out.println(item == null ? "Заявка удалена успешно." : "Ошибка удаления заявки.");
        return true;
    }
}
