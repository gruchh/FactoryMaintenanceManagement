import { createSlice } from "@reduxjs/toolkit";

const initialBreakdown = {
    id: null,
    eventDescription: null,
    startDate: null,
    endDate: null,
    severity: null,
    cause: null,
    usedParts: null,
    comments: null,
    machineId: null,
    employeeIds: [],
};

const initialState = {
    breakdowns: [],
    loading: false,
    error: null,
    success: null,
    currentBreakdown: initialBreakdown,
    page: 0,
    sortDirection: "ASC",
};

export const breakdownSlice = createSlice({
    name: "breakdowns",
    initialState,
    reducers: {
        getBreakdownsRequest: (state) => {
            state.loading = true;
            state.error = null;
            state.success = null;
        },
        getBreakdownsSuccess: (state, action) => {
            state.loading = false;
            state.breakdowns = action.payload;
            state.error = null;
            state.success = true;
        },
        getBreakdownsFailure: (state, action) => {
            state.loading = false;
            state.error = action.payload.message;
            state.success = null;
        },
        getPaginatedBreakdownsRequest: (state) => {
            state.loading = true;
            state.error = null;
            state.success = null;
        },
        getPaginatedBreakdownsSuccess: (state, action) => {
            state.loading = false;
            state.breakdowns = action.payload; // Oczekujemy listy BreakdownListItemDto
            state.error = null;
            state.success = true;
        },
        getPaginatedBreakdownsFailure: (state, action) => {
            state.loading = false;
            state.error = action.payload.message;
            state.success = null;
        },
        getBreakdownRequest: (state) => {
            state.loading = true;
            state.error = null;
            state.success = null;
            state.currentBreakdown = initialBreakdown;
        },
        getBreakdownSuccess: (state, action) => {
            state.loading = false;
            state.currentBreakdown = action.payload;
            state.error = null;
            state.success = true;
        },
        getBreakdownFailure: (state, action) => {
            state.loading = false;
            state.error = action.payload.message;
            state.success = null;
            state.currentBreakdown = initialBreakdown;
        },
        saveBreakdownRequest: (state) => {
            state.loading = true;
            state.error = null;
            state.success = null;
        },
        saveBreakdownSuccess: (state, action) => {
            state.loading = false;
            state.breakdowns.push(action.payload);
            state.error = null;
            state.success = true;
        },
        saveBreakdownFailure: (state, action) => {
            state.loading = false;
            state.error = action.payload.message;
            state.success = null;
        },
        updateBreakdownRequest: (state) => {
            state.loading = true;
            state.error = null;
            state.success = null;
        },
        updateBreakdownSuccess: (state, action) => {
            state.loading = false;
            const index = state.breakdowns.findIndex(
                (breakdown) => breakdown.id === action.payload.id
            );
            if (index !== -1) {
                state.breakdowns[index] = action.payload;
            }
            state.error = null;
            state.success = true;
        },
        updateBreakdownFailure: (state, action) => {
            state.loading = false;
            state.error = action.payload.message;
            state.success = null;
        },
        deleteBreakdownRequest: (state) => {
            state.loading = true;
            state.error = null;
            state.success = null;
        },
        deleteBreakdownSuccess: (state, action) => {
            state.loading = false;
            state.breakdowns = state.breakdowns.filter(
                (breakdown) => breakdown.id !== action.payload
            );
            state.error = null;
            state.success = true;
        },
        deleteBreakdownFailure: (state, action) => {
            state.loading = false;
            state.error = action.payload.message;
            state.success = null;
        },
        setPage: (state, action) => {
            state.page = action.payload;
        },
        setSortDirection: (state, action) => {
            state.sortDirection = action.payload;
        },
    },
});

export const {
    getBreakdownsRequest,
    getBreakdownsSuccess,
    getBreakdownsFailure,
    getPaginatedBreakdownsRequest,
    getPaginatedBreakdownsSuccess,
    getPaginatedBreakdownsFailure,
    getBreakdownRequest,
    getBreakdownSuccess,
    getBreakdownFailure,
    saveBreakdownRequest,
    saveBreakdownSuccess,
    saveBreakdownFailure,
    updateBreakdownRequest,
    updateBreakdownSuccess,
    updateBreakdownFailure,
    deleteBreakdownRequest,
    deleteBreakdownSuccess,
    deleteBreakdownFailure,
    setPage,
    setSortDirection,
} = breakdownSlice.actions;

export default breakdownSlice.reducer;