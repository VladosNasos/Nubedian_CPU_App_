export interface SocketDto {
    id: number;
    name: string;
}

export interface CpuDto {
    id?: number;
    brand: string;
    model: string;
    socketId: number;
    clockspeed: number;
    cores: number;
    threads: number;
    tdp: number;
    priceEur: number;
}
