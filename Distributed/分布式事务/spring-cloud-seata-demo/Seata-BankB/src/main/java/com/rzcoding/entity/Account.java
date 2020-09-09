package com.rzcoding.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="account")
@Data
@NoArgsConstructor
public class Account implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//主键id

    @Column(name = "userid")
	private String userid;//用户ID

    @Column(name = "username")
	private String username;//用户名称

    @Column(name = "amount")
	private Integer amount;//账户余额

    @Column(name = "update_time")
	private Long updateTime;//修改时间
}
