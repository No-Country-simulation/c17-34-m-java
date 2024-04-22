package com.c174.models.embed;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditMapper {
    // ------------- Responses to Entity -------------
    AuditResponse toAuditResponse(Audit audit);
    // ------------- Requests to Entity -------------
    Audit toAuditEntity(AuditResponse audit);
}
