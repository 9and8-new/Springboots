package com.example.demo.Domain.Common.Mapper;


import com.example.demo.Domain.Common.Dto.MemoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface MemoMapper {
    //추가
    @SelectKey(statement = "SELECT max(id) FROM testdb.tbl_memo",keyProperty = "id", before = false, resultType = Long.class ) //keyProperty = pk id / before = insert후 할건지 전에할건지 / resultType = 어떤 자료형으로 반환인지
    @Insert("insert into tbl_memo values(#{id},#{text},#{writer},#{createAt})") // Mybatis는 (?,?,?,?)가 아닌 <--- 처럼 적용함
    public int insert(MemoDto memoDto);
    //수정
    @Update("update tbl_memo set text=#{text},writer=#{writer} where id=#{id}")
    public int update(MemoDto memoDto);

    //삭제
    @Delete("delete from tbl_memo where id=#{id}")
    public int delete(@Param("id") Long memoId);

    @Select("select * from tbl_memo")
    public List<MemoDto> selectAll();

    @Select("select * from tbl_memo where ${type} like concat('%',#{keyword},'%')")
    public List<MemoDto> selectAllContains(String type, String keyword);

//    @Results(id="MemoResultMap", value={
//            @Result(property = "text",column = "text"),
//            @Result(property = "writer", column = "writer")
//    })
    @Select("select * from tbl_memo ")
    public List<Map<String, Objects> > selectAllWithResultMap(); // 한행마다 나오는값이 맵<스트링,오브젝트> 로 나옴

    //XML
    public int insertXML(MemoDto memoDto);
    public int updateXML(MemoDto memoDto);
    public int deleteXML(@Param("id") Long memoId);
    public MemoDto selectXML(@Param("id") Long id);
    public List<MemoDto> selectAllXML();
    public List< Map<String,Object> > selectAllMapXML();
    public List <Map<String,Object>> slectAllIfXMl(Map<String,Object> param);
    public List <Map<String,Object>> selectAllChooseXML(Map<String,Object> param);
    public List <Map<String,Object>> selectAllIfAndXML(Map<String,Object> param);
    public List< Map<String,Object> > selectForEachAnd(Map<String,Object> param);

}
