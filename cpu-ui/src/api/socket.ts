import axios from 'axios';
import { SocketDto } from '../types';

export const fetchSockets = () =>
    axios.get<SocketDto[]>('/api/sockets').then(r => r.data);
