package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу банковского сервиса, который позволяет добавлять в себя пользователя,
 * удалять пользователя, добавлять Аккаунт пользователю, найти пользователя по пасспорту или реквизитам,
 * перевести денежные средства с одного аккаунта на другой, получить список аккаунтов у пользователя.
 * @author XLoganxmenX
 * @version 1.0
 */
public class BankService {
    /**
     *  Хранит в себе список пользователей в виде HashMap,
     *  в котором ключами являются Пользователь, а значением - список Аккаунтов, данного пользователя.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод позволяет добавить Пользователя(user) в общий список пользователей (users).
     * @param user Пользователь, который будет добавлен в список
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод позволяет удалить Пользователя из общего списка пользователей (users) по номеру Пасспорта.
     * @param passport Номер пасспорта, по которому будет удален пользователь
     */
    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    /**
     * Метод позволяет добавить аккаунт(account) пользователю по номеру пасспорта(passport).
     * Сначала производится поиск Пользователя по номеру пасспорта, затем, в случае его нахождения,
     * пользователю добавляется аккаунт.
     * @param passport Номер пасспорта, по которому будет найден Пользователь
     * @param account Аккаунт, который будет добавлен пользователю
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccounts = getAccounts(user);
            if (!userAccounts.contains(account)) {
                userAccounts.add(account);
            }
        }
    }

    /**
     * Метод находит Пользователя из общего списка пользователей по номеру пасспорта.
     * @param passport Номер пасспорта, по которому будет найден Пользователь
     * @return возвращает Пользователя, у которого был найден введенный номер паспорта,
     * либо Null, в случае его отсутствия
     */
    public User findByPassport(String passport) {
        User rsl = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                rsl = user;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод находит Аккаунт по номеру пасспорта Пользователя и номеру реквизитов.
     * Сначала производится поиск Пользователя по номеру пасспорта, затем, в случае его нахождения,
     * у пользователя производится поиск Аккаунта по реквизитам.
     * @param passport Номер пасспорта, по которому будет найден Пользователь
     * @param requisite Номер банковских реквизитов, по которым будет найден аккаунт у Пользователя
     * @return возвращает найденный Аккаунт, либо Null, в случае его отсутствия
     */
    public Account findByRequisite(String passport, String requisite) {
        Account rsl = null;
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccounts = getAccounts(user);
            for (Account account : userAccounts) {
                if (account.getRequisite().equals(requisite)) {
                    rsl = account;
                    break;
                }
            }
        }
        return rsl;
    }

    /**
     * Метод, переносящий денежные средства с одного аккаунта на другой.
     * Сначала производится поиск Аккаунта, с которого будут переведены денежные средства,
     * затем производится поиск Аккаунта, на который будут переведены денежные средства.
     * Далее с одного счета списываются денежные средства, другому счет зачисляются эта же сумма.
     * @param srcPassport Номер пасспорта пользователя, с которого будут переведены денежные средства с Аккаунта
     * @param srcRequisite Реквизиты аккаунта, с которого будут переведены денежные средства
     * @param destPassport Номер пасспорта пользователя, на который будут переведены денежные средства с Аккаунта
     * @param destRequisite Реквизиты аккаунта, на который будут переведены денежные средства
     * @param amount Сумма перечисления
     * @return в случае успешного перечисления возвращается true
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод, позвращающий список Аккаунтов у Пользователя
     * @param user Пользователь, список Аккаунтов которого будет получен
     * @return список Аккаунтов введенного Пользователя
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
