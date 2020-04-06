package com.qjx.dao;

import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * Created by qincasin on 2020/3/26.
 */
//默认名字类名小写
@Repository
@Data
public class BookDao {

    private String label = "1";

}
