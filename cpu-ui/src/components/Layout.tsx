import { ReactNode } from 'react';
import { Link as RouterLink } from 'react-router-dom';
import {
    AppBar,
    Toolbar,
    Button,
    Container,
    Box,
    CssBaseline,
    Typography,
    useTheme,
} from '@mui/material';

type Props = { children: ReactNode };

export function Layout({ children }: Props) {
    const theme = useTheme();

    return (
        <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh', bgcolor: 'grey.100' }}>
            <CssBaseline />

            <AppBar position="static" elevation={3} sx={{ bgcolor: theme.palette.primary.main }}>
                <Container maxWidth="lg">
                    <Toolbar disableGutters>
                        <Button
                            component={RouterLink}
                            to="/"
                            disableRipple
                            sx={{
                                fontWeight: 700,
                                textTransform: 'none',
                                color: '#fff',
                                px: 2,
                                py: 1,
                                '&:hover': {
                                    color: '#fff',
                                    bgcolor: 'primary.dark',
                                },
                            }}
                        >
                            CPU Manager
                        </Button>

                        <Box sx={{ flexGrow: 1 }} />

                        <Button
                            component={RouterLink}
                            to="/"
                            color="inherit"
                            sx={{
                                textTransform: 'none',
                                '&:hover': {
                                    color: '#fff',
                                    bgcolor: 'primary.dark',
                                },
                            }}
                        >
                            Home
                        </Button>

                        <Button
                            component={RouterLink}
                            to="/create"
                            color="inherit"
                            sx={{
                                ml: 2,
                                textTransform: 'none',
                                '&:hover': {
                                    color: '#fff',
                                    bgcolor: 'primary.dark',
                                },
                            }}
                        >
                            Add CPU
                        </Button>
                    </Toolbar>
                </Container>
            </AppBar>

            <Box component="main" sx={{ flexGrow: 1, display: 'flex', justifyContent: 'center', py: 4 }}>
                <Container maxWidth="lg">{children}</Container>
            </Box>

            <Box component="footer" sx={{ py: 3, borderTop: '1px solid', borderColor: 'divider' }}>
                <Container maxWidth="lg">
                    <Typography variant="body2" align="center" color="text.secondary">
                        © {new Date().getFullYear()} CPU Manager. All rights reserved.
                    </Typography>
                </Container>
            </Box>
        </Box>
    );
}