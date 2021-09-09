import styled from 'styled-components';
import React, { Fragment, useState } from 'react';
import { useRouter } from 'next/router';
import { RootState } from '../../reducers';
import { actions } from '../../reducers/loginReducer';
import { useDispatch, useSelector } from 'react-redux';
import { LoginApi } from '../../pages/api/Sign';

const Login = (): JSX.Element => {
	const dispatch = useDispatch();
	const router = useRouter();
	const [inputId, setInputId] = useState<string>('');
	const [inputPw, setInputPw] = useState<string>('');

	const handleInputId = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputId(e.target.value);
	};

	const handleInputPw = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputPw(e.target.value);
	};
	const handleKeyPress = (e) => {
		if (e.key === 'Enter') {
			onClickLogin();
		}
	};

	const onClickLogin = () => {
		LoginApi(inputId, inputPw)
			.then(() => {
				if (inputId === 'admin') {
					dispatch(actions.loginStart({ id: true, admin_check: true }));
				} else {
					dispatch(actions.loginStart({ id: true, admin_check: false }));
				}
				alert('로그인 성공');
				sessionStorage.setItem('login-ing', 'true');
				router.push('/');
			})
			.catch((error) => {
				if (error.response.status === 401) {
					alert('아이디와 비밀번호를 확인하세요');
				}
				console.log(error);
			});
		// if (sessionStorage.getItem('user')) {
		// 	const userinfo = JSON.parse(sessionStorage.getItem('user'));
		// 	if (userinfo['id'] === inputId && userinfo['pw'] === inputPw) {
		// 		if (userinfo['id'] === 'admin') {
		// 			dispatch(actions.loginStart({ id: true, admin_check: true }));
		// 		} else {
		// 			dispatch(actions.loginStart({ id: true, admin_check: false }));
		// 		}
		// 		alert('로그인 성공');
		// 		sessionStorage.setItem('login-ing', 'true');

		// 		router.push('/');
		// 	} else {
		// 		alert('비밀번호가 틀렸습니다.');
		// 	}
		// } else {
		// 	alert('없는 아이디입니다.');
		// }
	};
	return (
		<Fragment>
			<LoginTitle>우희희 로그인</LoginTitle>
			<LoginForm>
				<LoginField>
					<LoginInput
						type="text"
						name="input_id"
						value={inputId}
						placeholder="아이디"
						onChange={handleInputId}
						onKeyPress={handleKeyPress}
					/>
					<Label htmlFor="input_id">아이디를 입력하세요.</Label>
				</LoginField>
				<LoginField>
					<LoginInput
						type="password"
						name="input_pw"
						value={inputPw}
						placeholder="비밀번호"
						onChange={handleInputPw}
						onKeyPress={handleKeyPress}
					/>
					<Label htmlFor="input_pw">비밀번호를 입력하세요.</Label>
				</LoginField>
			</LoginForm>
			<LoginField>
				<LoginButton onClick={onClickLogin}>Login</LoginButton>
			</LoginField>
		</Fragment>
	);
};

const LoginTitle = styled.h2`
	text-align: center;
`;

const LoginForm = styled.form`
	::-moz-placeholder {
		color: transparent;
	}
`;
const LoginField = styled.div`
	margin: 10px 0;
	position: relative;
	font-size: 14px;
	width: 100%;
	text-overflow: ellipsis;
	text-align: center;
`;
const LoginInput = styled.input`
	padding: 9px 0px 7px 9px;
	font-size: 14px;
	width: 16rem;
	height: 1.2rem;
	outline: none;
	background: #fafafa;
	border-radius: 3px;
	border: 1px solid #dbdbdb;

	&::placeholder {
		visibility: hidden;
	}
	&:not(:placeholder-shown) + label {
		transform: translateY(0);
		font-size: 11px;
	}
	&:not(:placeholder-shown) {
		padding-top: 14px;
		padding-bottom: 2px;
	}
`;

const Label = styled.label`
	position: absolute;
	pointer-events: none;
	left: 10px;
	padding-bottom: 15px;
	transform: translateY(10px);
	line-height: 6px;
	transition: all ease-out 0.1s;
	font-size: 14px;
	color: #999;
	padding-top: 6px;
`;

const LoginButton = styled.button`
	margin-top: 15px;
	width: 150px;
	padding: 6px 12px;
	color: #fff;
	background-color: cadetblue;
	border-radius: 5px;
	font-size: 13px;
	border: none;
`;
export default Login;
