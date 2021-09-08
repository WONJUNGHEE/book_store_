import type { AppProps } from 'next/app';

import Head from 'next/head';
import styled from 'styled-components';
import { Provider } from 'react-redux';
import Navigator from '../components/Navigator';
import '../styles/modal.css';
import configureStore from '../reducers/configureStore';
import { persistStore } from 'redux-persist';
import { PersistGate } from 'redux-persist/integration/react';

const store = configureStore();
const persistor = persistStore(store);

const TestApp = ({ Component, pageProps }: AppProps) => {
	return (
		<Provider store={store}>
			<PersistGate loading={null} persistor={persistor}>
				<Head>
					<title>우희희 북 스토어</title>
				</Head>
				<Main>
					<Title>우희희 북 스토어</Title>
					<Navigator />
					<Body>
						<Component {...pageProps} />
					</Body>
				</Main>
			</PersistGate>
		</Provider>
	);
};

const Main = styled.main`
	width: 100vw;
	height: 100vh;
	display: flex;
	flex-direction: column;
`;
const Title = styled.h1`
	text-align: center;
`;
const Body = styled.main`
	justify-content: center;
	display: flex;
	margin-top: 30px;
`;

export default TestApp;
