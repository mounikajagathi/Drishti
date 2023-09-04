package in.ults.ipms.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by hrskrs on 5/8/2017.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler io();

    Scheduler computation();

    Scheduler single();

    Scheduler trampoline();

    Scheduler newThread();

}
