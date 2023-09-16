package seb45.test.pettalk.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seb45.test.pettalk.common.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
}