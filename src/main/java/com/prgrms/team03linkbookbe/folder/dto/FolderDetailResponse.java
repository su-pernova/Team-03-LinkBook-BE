package com.prgrms.team03linkbookbe.folder.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.prgrms.team03linkbookbe.bookmark.dto.BookmarkResponse;
import com.prgrms.team03linkbookbe.comment.dto.CommentResponse;
import com.prgrms.team03linkbookbe.folder.entity.Folder;
import com.prgrms.team03linkbookbe.tag.entity.Tag;
import com.prgrms.team03linkbookbe.tag.entity.TagCategory;
import com.prgrms.team03linkbookbe.user.dto.UserSimpleResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FolderDetailResponse {

    private Long id;

    private String title;

    private String image;

    private String content;

    private Long originId;

    private Boolean isPinned;

    private Boolean isPrivate;

    private UserSimpleResponseDto user;

    private List<BookmarkResponse> bookmarks;

    private List<String> tags;

    private int likes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static FolderDetailResponse fromEntity(Folder folder) {
        return FolderDetailResponse.builder()
            .id(folder.getId())
            .title(folder.getTitle())
            .likes(folder.getLikes())
            .image(folder.getImage())
            .content(folder.getContent())
            .originId(folder.getOriginId())
            .isPinned(folder.getIsPinned())
            .isPrivate(folder.getIsPrivate())
            .tags(folder.getFolderTags().stream().map(folderTag -> folderTag.getTag().getName().getViewName()).collect(Collectors.toList()))
            .user(UserSimpleResponseDto.fromEntity(folder.getUser()))
            .bookmarks(folder.getBookmarks().stream().map(BookmarkResponse::fromEntity).collect(
                Collectors.toList()))
            .createdAt(folder.getCreatedAt())
            .build();
    }

}
