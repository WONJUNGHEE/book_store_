import React, { Fragment, useState, useRef, useEffect } from 'react';
import styled from 'styled-components';
import { useRouter } from 'next/router';
import { RootState } from '../reducers';

import { useDispatch, useSelector } from 'react-redux';
import { actions } from '../reducers/loginReducer';

const Navigation = (): JSX.Element => {
	const { id, admin_check } = useSelector((state: RootState) => state.loginReducer);
	const [inputNav, setInputNav] = useState<boolean>(false);

	const router = useRouter();
	const dispatch = useDispatch();

	const handleClick = (e) => {
		e.preventDefault();
		router.push(e.target.href);
	};

	const logout = () => {
		sessionStorage.setItem('login-ing', 'false');
		dispatch(actions.loginStart({ id: false, admin_check: false }));
		router.push('/LoginPage');
	};

	const openNav = (): void => {
		inputNav ? setInputNav(false) : setInputNav(true);
	};
	const CatalogClick = (e): void => {
		e.preventDefault();

		router.push(e.target.href);
		setInputNav(false);
	};
	return (
		<Fragment>
			<Navbar>
				<NavMenu>
					<NavItem>
						<a href={'/'} onClick={handleClick}>
							홈
						</a>
					</NavItem>
					<NavItem>
						<a onClick={openNav}>카테고리</a>
						<NavItemCatalog className={inputNav ? 'open' : 'close'}>
							<a href="/Nav/CatalogPage" onClick={CatalogClick}>
								전체보기
							</a>
							<a href="/Nav/EduPage" onClick={CatalogClick}>
								교육
							</a>
							<a href="/Nav/Cartoonpage" onClick={CatalogClick}>
								만화
							</a>
							<a href="/Nav/EduPage" onClick={CatalogClick}>
								사회
							</a>
						</NavItemCatalog>
					</NavItem>
					<NavItem>
						<a href="/Cart_page" onClick={handleClick}>
							장바구니
						</a>
					</NavItem>
				</NavMenu>
				<NavSign>
					<NavItem>
						<NavLog className={id ? 'logout' : 'login'}>
							<div className="in">
								<a href="/LoginPage" onClick={handleClick}>
									로그인
								</a>
							</div>
							<div className="out">
								<a
									className={admin_check ? 'book_add' : 'no'}
									href="/Book_register_page"
									onClick={handleClick}
								>
									책 등록하기
								</a>
								<a href="/MyPage_page" onClick={handleClick}>
									마이페이지
								</a>
								<a href="/LoginPage" onClick={logout}>
									로그아웃
								</a>
							</div>
						</NavLog>
					</NavItem>
				</NavSign>
			</Navbar>
		</Fragment>
	);
};

const Navbar = styled.nav`
	height: 60px;
	width: 100%;
	text-align: center;
	display: flex;
	align-items: center;
	background-color: aliceblue;
`;
const NavMenu = styled.ul`
	list-style: none;
	height: 60px;
	margin: auto;
	padding: 0;
`;
const NavSign = styled.ul`
	list-style: none;
	height: 60px;
	margin: auto;
	padding: 0;

	@media screen and (max-width: 768px) {
		display: none;
	}
`;
const NavItem = styled.li`
	display: inline-block;
	a {
		color: blue;
		display: block;
		position: relative;
		font-size: 16px;
		font-weight: 500;
		text-decoration: none;
		margin: 20px;
	}
`;
const NavItemCatalog = styled.div`
	z-index: 11;
	position: relative;
	&.open {
		float: left;
		background-color: aliceblue;
		text-align: center;

		border-radius: 0 0 20px 20px;
		a {
			font-size: 16px;
			font-weight: 500;
			text-decoration: none;
			margin: 25px;
		}
	}
	&.close {
		display: none;
	}
`;

const NavLog = styled.div`
	&.login {
		display: inline-block;

		background-color: aliceblue;
		text-align: center;
		.out {
			display: none;
		}
	}
	&.logout {
		.out {
			display: flex;
			flex-direction: row;

			.no {
				display: none;
			}
		}
		display: inline-block;
		background-color: aliceblue;
		text-align: center;
		flex-direction: row;
		position: relative;
		.in {
			display: none;
		}
	}
`;

export default Navigation;
