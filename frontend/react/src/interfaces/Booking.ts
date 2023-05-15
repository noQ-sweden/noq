interface Ibooking {
    id: number;
    status: 'PENDING' | 'RESERVED' | 'CANCELLED';
    name: string;
    address: string;
    date: string;
}

export default Ibooking