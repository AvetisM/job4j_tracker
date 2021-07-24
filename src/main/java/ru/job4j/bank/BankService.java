package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает работу банка
 * Можно добавлять нового клиента
 * Добавлять счет для клиента
 * Осуществлять денежные переводы
 * @author Avetis Mkhitaryants
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение клиентов банка и их счетов осуществляется в
     * коллекции типа Hashmap
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход клиента банка и
     * добавляет в коллекцию, если он его нет в коллекции
     * @param user клиент банка который добавляется в коллекцию
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод принимает на вход паспортные данные и счет
     * Ищет клиента по паспорту и добавляет счет в
     * список счетов пользователя, если его там нет.
     * @param passport данные по которым ищется клиент
     * @param account счет который добавляется в список счетов
     */
    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = users.get(user.get());
            if (!accounts.contains(account)) {
                accounts.add(account);
                users.put(user.get(), accounts);
            }
        }
    }

    /**
     * Метод ищет пользователя по паспорту
     * @param passport паспорт по которому ищется клиент
     * @return возвращает клиента банка либо null если не удалось найти
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter(u -> u.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Метод ищет счет клиента по реквизитам
     * @param passport паспорт для поиска клиента
     * @param requisite реквизиты для поиска счета из списка счетов клиента
     * @return возвращает найденный счет клиента или null если счет не найден
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {

        Optional<User> user = findByPassport(passport);

        return user.flatMap(value -> users.get(value).stream()
                .filter(s -> requisite.equals(s.getRequisite()))
                .findFirst());
    }

    /**
     * Метод осуществляет перевод денежных средств с одного счета на другой
     * @param srcPassport паспорт клиента отправителя
     * @param srcRequisite реквизиты счета отправителя
     * @param destPassport паспорт клиента получателя
     * @param destRequisite реквизиты счета получателя
     * @param amount сумма которую необходимо перевести
     * @return возращает результат операции true если операция прошла успешно
     * false в обратном случае
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {

        Optional<Account> sourceAccount = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destAccount = findByRequisite(destPassport, destRequisite);

        if (sourceAccount.isPresent()
                && destAccount.isPresent()
                && sourceAccount.get().getBalance() >= amount) {
            sourceAccount.get().setBalance(sourceAccount.get().getBalance() - amount);
            destAccount.get().setBalance(destAccount.get().getBalance() + amount);
            return true;
        } else {
            return false;
        }
    }
}
