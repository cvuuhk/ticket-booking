package edu.hhuc.ticket_booking.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户帐号表
 */
@Data
@Table(name = "account")
@Entity
public class Account implements UserDetails{
    /**
     * 用户ID，主键，自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;
    
    /**
     * 昵称，不可重复
     */
    @Column(name = "name", nullable = false)
    private String name;
    
    /**
     * 密码，Bcrypt加密
     */
    @Column(name = "password", nullable = false)
    private String password;
    
    /**
     * 电子邮箱
     */
    @Column(name = "email")
    private String email = "NULL";
    
    /**
     * 注册时间戳，自动生成
     */
    @Column(name = "register_time")
    private LocalDateTime registerTime;
    
    /**
     * 账户是否过期
     */
    @Column(name = "account_expired", nullable = false)
    private boolean accountExpired = false;
    
    /**
     * 密码是否过期
     */
    @Column(name = "credential_expired", nullable = false)
    private boolean credentialExpired = false;
    
    /**
     * 账户是否被锁定
     */
    @Column(name = "locked", nullable = false)
    private boolean locked = false;
    
    /**
     * 账户是否可用
     */
    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    List<Role> roles;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    @Override
    public String getUsername(){ return name; }
    @Override
    public boolean isAccountNonExpired(){ return !accountExpired; }
    @Override
    public boolean isAccountNonLocked(){ return !locked; }
    @Override
    public boolean isCredentialsNonExpired(){ return !credentialExpired; }
    @Override
    public boolean isEnabled(){ return enabled; }
}