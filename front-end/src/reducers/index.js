import { SIGNED_IN } from '../constants';

let user = {
    email: null,
    token: null
}

export default (state = user, action) => {
    switch (action.type) {
      case SIGNED_IN:
        const { email, token } = action;
        user = {
          email,
          token
        }
        return user;
      default:
        return state;
    }
  }