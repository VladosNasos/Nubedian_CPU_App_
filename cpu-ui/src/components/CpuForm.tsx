import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useForm, Controller } from 'react-hook-form';
import { fetchSockets } from '../api/socket';
import { fetchCpu, createCpu, updateCpu } from '../api/cpu';
import { SocketDto, CpuDto } from '../types';
import { Layout } from './Layout';
import {
    Paper,
    Typography,
    TextField,
    FormControl,
    InputLabel,
    Select,
    MenuItem,
    Button,
    CircularProgress,
    Box,
    Stack,
    Container,
} from '@mui/material';

export function CpuForm() {
    const { id } = useParams<{ id: string }>();
    const isEdit = Boolean(id);
    const navigate = useNavigate();
    const { register, control, handleSubmit, setValue } = useForm<CpuDto>();
    const [sockets, setS] = useState<SocketDto[] | null>(null);

    useEffect(() => {
        fetchSockets().then(setS);
        if (isEdit) {
            fetchCpu(Number(id)).then((cpu) =>
                Object.entries(cpu).forEach(([k, v]) => setValue(k as keyof CpuDto, v as any))
            );
        }
    }, [id, isEdit, setValue]);

    if (sockets === null) {
        return (
            <Layout>
                <Box sx={{ display: 'flex', justifyContent: 'center', pt: 8 }}>
                    <CircularProgress />
                </Box>
            </Layout>
        );
    }

    const onSubmit = (data: CpuDto) => {
        const action = isEdit ? updateCpu(Number(id), data) : createCpu(data);
        action.then(() => navigate('/'));
    };

    return (
        <Layout>
            <Container maxWidth="sm" sx={{ mt: 4, mb: 4 }}>
                <Paper elevation={4} sx={{ p: 4 }}>
                    <Typography variant="h5" gutterBottom color="primary" align="center">
                        {isEdit ? 'Edit CPU' : 'Create New CPU'}
                    </Typography>

                    <Box component="form" onSubmit={handleSubmit(onSubmit)} noValidate>
                        <Stack spacing={3}>
                            <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
                                <TextField label="Brand" fullWidth required {...register('brand')} />
                                <TextField label="Model" fullWidth required {...register('model')} />
                            </Stack>
                            <FormControl fullWidth required>
                                <InputLabel id="socket-label">Socket</InputLabel>
                                <Controller
                                    name="socketId"
                                    control={control}
                                    defaultValue={sockets[0]?.id ?? ''}
                                    render={({ field }) => (
                                        <Select {...field} labelId="socket-label" label="Socket">
                                            {sockets.map((s) => (
                                                <MenuItem key={s.id} value={s.id}>
                                                    {s.name}
                                                </MenuItem>
                                            ))}
                                        </Select>
                                    )}
                                />
                            </FormControl>
                            <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
                                <TextField label="Clockspeed (GHz)" type="number" inputProps={{ step: 0.1 }} fullWidth required {...register('clockspeed', { valueAsNumber: true })} />
                                <TextField label="Cores" type="number" fullWidth required {...register('cores', { valueAsNumber: true })} />
                            </Stack>
                            <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
                                <TextField label="Threads" type="number" fullWidth required {...register('threads', { valueAsNumber: true })} />
                                <TextField label="TDP (W)" type="number" fullWidth required {...register('tdp', { valueAsNumber: true })} />
                            </Stack>
                            <TextField label="Price (€)" type="number" inputProps={{ step: 0.01 }} fullWidth required {...register('priceEur', { valueAsNumber: true })} />
                            <Stack direction="row" spacing={2} justifyContent="center">
                                <Button variant="outlined" onClick={() => navigate(-1)}>
                                    Cancel
                                </Button>
                                <Button variant="contained" color="primary" type="submit">
                                    {isEdit ? 'Save Changes' : 'Create CPU'}
                                </Button>
                            </Stack>
                        </Stack>
                    </Box>
                </Paper>
            </Container>
        </Layout>
    );
}