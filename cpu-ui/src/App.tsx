import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { CpuList } from './components/CpuList';
import { CpuForm } from './components/CpuForm';

export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<CpuList />} />
                <Route path="/create" element={<CpuForm />} />
                <Route path="/edit/:id" element={<CpuForm />} />
            </Routes>
        </BrowserRouter>
    );
}
