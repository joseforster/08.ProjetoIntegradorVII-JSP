/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author forster
 */
public interface IDAO <T> {
    
    public boolean save (T model);
    public ArrayList<T> getLista();
    public T getById (int id);
    public boolean destroy (int id);
    
}
