// cpu-ui/src/api/cpu.ts
import axios from 'axios';
import { CpuDto } from '../types';

const api = axios.create({
    baseURL: '/api',
    withCredentials: true,
});

export const fetchCpus = () => api.get<CpuDto[]>('/cpus').then(r => r.data);
export const fetchCpu  = (id: number) => api.get<CpuDto>(`/cpus/${id}`).then(r => r.data);
export const createCpu = (cpu: CpuDto) => api.post('/cpus', cpu);
export const updateCpu = (id: number, cpu: CpuDto) => api.put(`/cpus/${id}`, cpu);
export const deleteCpu = (id: number) => api.delete(`/cpus/${id}`);
