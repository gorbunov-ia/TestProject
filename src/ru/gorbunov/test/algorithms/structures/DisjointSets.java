package ru.gorbunov.test.algorithms.structures;

import java.util.Scanner;

/**
 * В базе данных есть n таблиц, пронумерованных от 1 до n, над од-
 * ним и тем же множеством столбцов (атрибутов). Каждая таблица со-
 * держит либо реальные записи в таблице, либо символьную ссылку на
 * другую таблицу. Изначально все таблицы содержат реальные записи,
 * и i-я таблица содержит r_i записей. Ваша цель — обработать m запро-
 * сов типа (destination_i , source_i ):
 * 1. Рассмотрим таблицу с номером destination_i . Пройдясь по цепоч-
 * ке символьных ссылок, найдём номер реальной таблицы, на ко-
 * торую ссылается эта таблица:
 * пока таблица destination i содержит символическую ссылку:
 * destination_i ← symlink(destination_i )
 * 2. Сделаем то же самое с таблицей source_i .
 * 3. Теперь таблицы destination_i и source_i содержат реальные запи-
 * си. Если destination_i != source_i, скопируем все записи из таблицы
 * source_i в таблицу destination_i , очистим таблицу source_i и пропи-
 * шем в неё символическую ссылку на таблицу destination_i.
 * 4. Выведем максимальный размер среди всех n таблиц. Размером
 * таблицы называется число строк в ней. Если таблица содержит
 * символическую ссылку, считаем её размер равным нулю.
 */
public class DisjointSets {
    private int[] parents;
    private int[] tableRows;
    private int maxRowCount = 0;

    public static void main(String[] args) {
        new DisjointSets().process();
    }

    private void process() {
        final Scanner scanner = new Scanner(System.in);
        int tableCount = scanner.nextInt();
        int commandCount = scanner.nextInt();
        tableRows = new int[tableCount];
        parents = new int[tableCount];
        for (int i = 1; i <= tableCount; i++) {
            setTableRows(i, scanner.nextInt());
            makeSet(i);
        }
        for (int i = 0; i < commandCount; i++) {
            final int destinationTable = scanner.nextInt();
            final int sourceTable = scanner.nextInt();
            union(destinationTable, sourceTable);
            System.out.println(maxRowCount);
        }
    }

    private void setTableRows(int table, int rowCount) {
        tableRows[table - 1] = rowCount;
        if (rowCount > maxRowCount) {
            maxRowCount = rowCount;
        }
    }

    private int getRowCount(int table) {
        return tableRows[table - 1];
    }

    private void makeSet(int table) {
        parents[table - 1] = table - 1;
    }

    private int find(int table) {
        table = table - 1;
        if (table != parents[table]) {
            parents[table] = find(parents[table] + 1) - 1;
        }
        return parents[table] + 1;
    }

    private void setParent(int table, int parent) {
        parents[table - 1] = parent - 1;
    }

    private void union(int destination, int source) {
        final int trgTable = find(destination);
        final int srcTable = find(source);
        final int srcRowCount = getRowCount(srcTable);
        setTableRows(srcTable, 0);
        setTableRows(trgTable, getRowCount(trgTable) + srcRowCount);
        setParent(srcTable, trgTable);
    }

}
