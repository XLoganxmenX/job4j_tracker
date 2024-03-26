package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item by id";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete item ===");
        int id = input.askInt("Enter id: ");
        tracker.delete(id);
        Item item = tracker.findById(id);
        out.println(item == null ? "Заявка удалена успешно." : "Ошибка удаления заявки.");
        return true;
    }
}
