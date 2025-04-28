import { useEffect, useState } from 'react';
import { fetchCpus, deleteCpu } from '../api/cpu';
import { CpuDto } from '../types';
import { Link as RouterLink } from 'react-router-dom';
import { Layout } from './Layout';
import {
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography,
    Stack,
    Box,
    Button,
} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

export function CpuList() {
    const [cpus, setCpus] = useState<CpuDto[]>([]);

    useEffect(() => {
        fetchCpus().then(setCpus);
    }, []);

    return (
        <Layout>
            <Paper elevation={4} sx={{ mx: 'auto', width: '100%', maxWidth: 960 }}>
                <Box
                    sx={{
                        px: 4,
                        py: 3,
                        bgcolor: 'primary.light',
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'space-between',
                    }}
                >
                    <Stack spacing={0.5}>
                        <Typography variant="h5" fontWeight={700} color="primary.contrastText">
                            CPU List
                        </Typography>
                        <Box sx={{ height: 4, width: 48, bgcolor: 'primary.contrastText', borderRadius: 2 }} />
                    </Stack>

                    <Button
                        component={RouterLink}
                        to="/create"
                        variant="contained"
                        startIcon={<AddIcon />}
                        sx={{
                            textTransform: 'none',
                            boxShadow: 'none',
                            color: '#fff',
                            '&:hover': {
                                boxShadow: 'none',
                                backgroundColor: 'primary.dark',
                                color: '#fff',
                            },
                        }}
                    >
                        New CPU
                    </Button>
                </Box>

                <TableContainer>
                    <Table stickyHeader>
                        <TableHead>
                            <TableRow>
                                {['Brand', 'Model', 'Socket', 'Actions'].map((h) => (
                                    <TableCell key={h} sx={{ fontWeight: 700 }}>
                                        {h}
                                    </TableCell>
                                ))}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {cpus.map((cpu) => (
                                <TableRow key={cpu.id} hover>
                                    <TableCell>{cpu.brand}</TableCell>
                                    <TableCell>{cpu.model}</TableCell>
                                    <TableCell>{cpu.socketId}</TableCell>
                                    <TableCell>
                                        <Stack direction="row" spacing={1}>
                                            <Button
                                                component={RouterLink}
                                                to={`/edit/${cpu.id}`}
                                                size="small"
                                                variant="outlined"
                                                startIcon={<EditIcon fontSize="small" />}
                                                sx={{ textTransform: 'none' }}
                                            >
                                                Edit
                                            </Button>
                                            <Button
                                                size="small"
                                                variant="outlined"
                                                color="error"
                                                startIcon={<DeleteIcon fontSize="small" />}
                                                onClick={() => deleteCpu(cpu.id!).then(() => setCpus((prev) => prev.filter((x) => x.id !== cpu.id)))}
                                                sx={{
                                                    textTransform: 'none',
                                                    borderColor: 'error.main',
                                                    color: 'error.main',
                                                    '&:hover': {
                                                        bgcolor: 'error.main',
                                                        color: '#fff',
                                                        borderColor: 'error.main',
                                                    },
                                                }}
                                            >
                                                Delete
                                            </Button>
                                        </Stack>
                                    </TableCell>
                                </TableRow>
                            ))}

                            {cpus.length === 0 && (
                                <TableRow>
                                    <TableCell colSpan={4} align="center" sx={{ py: 6, color: 'text.secondary' }}>
                                        <Stack spacing={2} alignItems="center">
                                            <Typography>No CPUs found</Typography>
                                            <Button component={RouterLink} to="/create" startIcon={<AddIcon />} variant="outlined" sx={{ textTransform: 'none' }}>
                                                Add your first CPU
                                            </Button>
                                        </Stack>
                                    </TableCell>
                                </TableRow>
                            )}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Paper>
        </Layout>
    );
}