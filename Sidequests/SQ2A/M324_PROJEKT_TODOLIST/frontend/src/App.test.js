import { render, screen, fireEvent } from '@testing-library/react';
import App from './App';

test('renders correctly', () => {
  render(<App />);
  expect(screen.getByText('ToDo Liste')).toBeInTheDocument();
  expect(screen.getByRole('button', { name: 'Absenden' })).toBeInTheDocument();
});

test('handles input changes', () => {
  render(<App />);
  const input = screen.getByRole('textbox');
  fireEvent.change(input, { target: { value: 'New Task' } });
  expect(input.value).toBe('New Task');
});