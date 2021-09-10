import React from 'react';
import _ from 'lodash';
import styled from 'styled-components';

const Pagination = (props) => {
	const { itemsCount, pageSize, currentPage, onPageChange } = props; // 각각 아이템(영화목록) 개수, 한 페이지에 보여줄 아이템(영화목록) 개수

	const pageCount = Math.ceil(itemsCount / pageSize); // 몇 페이지가 필요한지 계산

	const pages = _.range(1, pageCount + 1); // 마지막 페이지에 보여줄 컨텐츠를 위해 +1, https://lodash.com/docs/#range 참고

	return (
		<Navbar>
			{' '}
			{/* VSCode 입력: nav>ul.pagination>li.page-item>a.page-link */}
			<NavMenu>
				{pages.map((page) => (
					<NavItem
						key={page}
						className={page === currentPage ? 'page-item active' : 'page-item'} // Bootstrap을 이용하여 현재 페이지를 시각적으로 표시
						style={{ cursor: 'pointer' }}
					>
						<a className="page-link" onClick={() => onPageChange(page)}>
							{page}
						</a>{' '}
						{/* 페이지 번호 클릭 이벤트 처리기 지정 */}
					</NavItem>
				))}
			</NavMenu>
		</Navbar>
	);
};
const Navbar = styled.nav`
	text-align: center;
	display: flex;
	align-items: center;
`;
const NavMenu = styled.ul`
	list-style: none;
	height: 60px;
	margin: auto;
	margin-top: 20px;
	padding: 0;
	& .active {
		background-color: #62bbc7;
		color: white;
	}
`;

const NavItem = styled.li`
	display: inline-block;
	margin: 5px;
	a {
		display: block;
		position: relative;
		font-size: 16px;
		font-weight: 500;
		text-decoration: none;
		border: 1px solid blue;
		padding: 10px;
	}
`;
export default Pagination;
