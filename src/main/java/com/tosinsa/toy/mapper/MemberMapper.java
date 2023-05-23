package com.tosinsa.toy.mapper;

import com.tosinsa.toy.domain.Member;
import com.tosinsa.toy.web.MemberForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberFormToMember(MemberForm memberForm);

}
