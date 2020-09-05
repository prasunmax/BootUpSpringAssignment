import { SIGNED_IN, SIGNED_OUT } from '../constants';

export function logUser(email, token) {
  const action = {
    type: SIGNED_IN,
    email,
    token
  }
  return action;
}

export function logOut() {
  const action = {
    type: SIGNED_OUT
  }
  return action;
}