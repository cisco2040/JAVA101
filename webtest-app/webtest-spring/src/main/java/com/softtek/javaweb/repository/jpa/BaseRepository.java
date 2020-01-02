package com.softtek.javaweb.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository <T, ID> extends JpaRepository<T, ID> {
}
