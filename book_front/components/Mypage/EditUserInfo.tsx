import React, { useState } from 'react';
import Modal from '../Modal/Modal';
import styled from 'styled-components';
import { SignUpApi } from '../../pages/api/Sign';
import axios from 'axios';

const EditUserInfo = (): JSX.Element => {
	const [ModalOpen, setModalOpen] = useState(false);
	const [inputPw, setInputPw] = useState<string>('');
	const [inputName, setInputName] = useState<string>('');
	const [inputPhone, setInputPhone] = useState<string>('');
	const [inputEmail, setInputEmail] = useState<string>('');
	const [inputAddress, setInputAddress] = useState<string>('');

	const userId = JSON.parse(sessionStorage.getItem('login_info'));

	const handleInputPw = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputPw(e.target.value);
	};
	const handleInputName = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputName(e.target.value);
	};
	const handleInputPhone = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputPhone(e.target.value);
	};
	const handleInputEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputEmail(e.target.value);
	};
	const handleInputAddress = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputAddress(e.target.value);
	};
	const openModal = (): void => {
		setModalOpen(true);
	};
	const closeModal = (): void => {
		setModalOpen(false);
	};
	const handleKeyPress = (e): void => {
		if (e.key === 'Enter') {
			sign();
		}
	};

	const sign = (): void => {
		if (
			inputPw === '' ||
			inputName === '' ||
			inputPhone === '' ||
			inputEmail === '' ||
			inputAddress === ''
		) {
			alert('정보를 모두 입력해주세요.');
		} else {
			axios
				.put(`http://localhost:50001/users/${userId[1]}`, {
					userName: inputName,
					myId: userId[2],
					pwd: inputPw,
					phoneNum: inputPhone,
					email: inputEmail,
					address: inputAddress,
				})
				.then((response) => {
					alert('정보수정이 완료되었습니다.');
					closeModal();
				})
				.catch((error) => {
					if (error.response.status === 400) {
						alert('회원정보를 다시 확인해주세요');
					}
				});
		}
	};
	return (
		<SignField>
			<SignButton onClick={openModal}>정보 수정</SignButton>
			<Modal open={ModalOpen} className="login-button" close={closeModal} header="회원가입">
				<InputData>
					<SignField>
						<SignInput
							id="password"
							type="password"
							placeholder="비밀번호"
							onChange={handleInputPw}
							onKeyPress={handleKeyPress}
						/>
						<Label htmlFor="password">비밀번호</Label>
					</SignField>
					<SignField>
						<SignInput
							id="name"
							type="text"
							placeholder="이름"
							onChange={handleInputName}
							onKeyPress={handleKeyPress}
						/>
						<Label htmlFor="name">이름</Label>
					</SignField>
					<SignField>
						<SignMail>
							<SignInput
								id="email"
								type="text"
								placeholder="이메일"
								onChange={handleInputEmail}
								onKeyPress={handleKeyPress}
							/>
							<Label htmlFor="email">이메일</Label>
						</SignMail>
					</SignField>
					<SignField>
						<SignInput
							id="phone"
							type="text"
							placeholder="전화번호"
							onChange={handleInputPhone}
							onKeyPress={handleKeyPress}
						/>
						<Label htmlFor="phone">전화번호</Label>
					</SignField>
					<SignField>
						<SignInput
							id="address"
							type="text"
							placeholder="주소"
							onChange={handleInputAddress}
							onKeyPress={handleKeyPress}
						/>
						<Label htmlFor="address">주소</Label>
					</SignField>
					<SignButton onClick={sign} title="login">
						수정하기
					</SignButton>
				</InputData>
			</Modal>
		</SignField>
	);
};

const SignField = styled.div`
	margin: 10px 0;
	position: relative;
	font-size: 14px;
	width: 100%;
	text-overflow: ellipsis;
	text-align: center;
`;
const SignInput = styled.input`
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

const SignButton = styled.button`
	margin-top: 15px;
	width: 150px;
	padding: 6px 12px;
	color: #fff;
	background-color: cadetblue;
	border-radius: 5px;
	font-size: 13px;
	border: none;
`;

const InputData = styled.div`
	margin: 15px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	& > input {
		padding: 10px;
		margin: 5px;
	}
	& > button {
		margin: 10px;
		width: 100px;
		height: 30px;
		align-self: center;
	}
`;
const EmailSelect = styled.select`
	margin-top: 10px;
`;
const SignMail = styled.div`
	display: flex;
	flex-direction: column;
`;
export default EditUserInfo;
