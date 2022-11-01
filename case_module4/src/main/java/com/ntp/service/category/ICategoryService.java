package com.ntp.service.category;

import com.ntp.model.Category;
import com.ntp.service.IGeneralService;

public interface ICategoryService extends IGeneralService<Category> {
    void softDelete(Category category);

    Category findCatetoryByName(String name);
}
