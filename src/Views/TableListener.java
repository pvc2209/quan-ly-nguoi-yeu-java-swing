/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.util.EventListener;

/**
 *
 * @author pvc
 */
public interface TableListener extends EventListener{
    public void themNguoiYeu();
    public void suaNguoiYeu(int index);
    public void xoaNguoiYeu(int index);
    public void chonNguoiYeu(int index);
    public void timKiem(String name);
}
