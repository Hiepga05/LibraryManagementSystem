package com.library.repository;

import java.util.List;

public interface Repository<T> {

    boolean them(T obj);

    boolean sua(T obj);

    boolean xoa(int id);

    T timTheoId(int id);

    T timTheoMa(String ma);

    List<T> layTatCa();
}