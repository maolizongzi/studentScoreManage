<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table style="width: 100%;">
	<tr>
		<td>
			<div class="navbar-left form-inline">
			
			<input type="hidden" id="pageId" name="pageId" value="${pageView.currentPage}">
					共有<span id="itemTotal">${pageView.recordCount}</span> 条记录，当前第<span
						>${pageView.currentPage}</span> 页,共 <span id="pageTotal">${pageView.pageCount}</span>页/每页${pageView.pageSize}条
			</div>
		</td>
		<td style="width: 420px;">
			<button class="btn green" onclick="page(1);">首页</button>
			<button class="btn green" onclick="goPage(-1);">上一页</button>
			<button class="btn green" onclick="goPage(1);">下一页</button>
			<button class="btn green" onclick="page($('#pageTotal').text());">尾页</button>
			<input type="text" id="page_id" style="width: 50px; margin-bottom: 0px;">
			<button class="btn green" onclick="page($('#page_id').val())">转到</button>
		</td>
	</tr>
</table>
