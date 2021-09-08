import React from 'react';
import Login from '../components/login/Login';
import SignUp from '../components/login/SignUp';
import styled from 'styled-components';

const LoginPage = () => {
	return (
		<LoginMain>
			<Login />
			<SignUp />
		</LoginMain>
	);
};

const LoginMain = styled.div``;
export default LoginPage;
