package com.mapo.eco100.entity.board

data class BoardModifyForm(
    private val boardId: Long,
    private val title: String,
    private val contents: String,
    private val isDeletedImage: Boolean
)