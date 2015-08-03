package com.ggingenieria.estacion.modelos;

/**
 * Created by francisco on 29/07/15.
 */
public class Paginacion {
    int totalItems;
    int itemsPerPage;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
