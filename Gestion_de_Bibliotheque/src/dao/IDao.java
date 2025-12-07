/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author HP
 * @param <T>
 */
public interface IDao<T> {

    public boolean insert(T o) throws Exception;

    public boolean update(T o) throws Exception;

    public boolean delete(T o) throws Exception;

    public List<T> findAll() throws Exception;

    public T findById(T o) throws Exception;
}
