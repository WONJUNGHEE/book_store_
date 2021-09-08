import { createAction, ActionType, createReducer } from 'typesafe-actions';
import { login } from '../interfaces/index';
// 상태 초기화
const initialState: login = {
	id: false,
	admin_check: false,
};

// 액션타입 선언
export const LOGIN_START = 'loginReducer/LOGIN_START';

export const LOGOUT_START = 'loginReducer/LOGOUT_START';

// 액션함수 선언

export const loginStart = createAction(LOGIN_START)<login>();
export const logoutStart = createAction(LOGOUT_START)();

// 액션 객체타입
export const actions = { loginStart, logoutStart };
type LoginReducerActions = ActionType<typeof actions>;

// 리듀서 추가
const loginReducer = createReducer<login, LoginReducerActions>(initialState, {
	[LOGOUT_START]: () => ({
		id: false,
		admin_check: false,
	}),
	[LOGIN_START]: (state, action) => {
		console.log(state.id);
		return {
			id: action.payload.id,
			admin_check: action.payload.admin_check,
		};
	},
});

export default loginReducer;
